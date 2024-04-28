var express = require('express');
var router = express.Router();
var Model = require('../../../models/index');
const Sequelize = require("sequelize");
var Response = require('../../Response');
var statusCodes = require('../../statusCodes');
var { validateUserToken } = require("../../../middlewares/validateToken");
var { encryptResponse, decryptRequest } = require("../../../middlewares/crypt");

/**
 * Balance transfer route
 * @path                 - /api/balance/transfer
 * @middleware
 * @param to_account     - Amount to be transferred to this account
 * @param amount         - Amount to be transferred
 * @return               - Status
 */
router.post('/', [validateUserToken, decryptRequest], (req, res) => {
    var r = new Response();
    let from_account = req.account_number;
    let to_account = req.body.to_account;
    let amount = req.body.amount;

    Model.users.findOne({
        where: {
            account_number: from_account
        },
        attributes: ["balance"]
    }).then((data) => {
        if(data) {
            if(data.dataValues.balance >= amount) {
                Model.beneficiaries.findAll({
                    where: {
                        account_number: from_account,
                        approved: true
                    },
                    attributes: ["beneficiary_account_number"]
                }).then((data) => {
                    if (data) {
                        let arr = data.map((elem) => {return elem.beneficiary_account_number});
                        if (arr.includes(to_account)) {
                            Model.transactions.create({
                                from_account: from_account,
                                to_account: to_account,
                                amount: amount
                            }).then(()=>{
                                Model.users.update({
                                    balance: Sequelize.literal(`balance - ${amount}`)
                                }, {
                                    where: {
                                        account_number: from_account
                                    }
                                }).then(()=>{
                                    Model.users.update({
                                        balance: Sequelize.literal(`balance + ${amount}`)
                                    }, {
                                        where: {
                                            account_number: to_account
                                        }
                                    }).then(() => {
                                        r.status = statusCodes.SUCCESS;
                                        r.data = {
                                            "message": "Success"
                                        }
                                        return res.json(encryptResponse(r));
                                    });
                                });
                            });        
                        } else {
                            r.status = statusCodes.BAD_INPUT;
                            r.data = {
                                "message": "Receiver account number not in beneficiary list"
                            }
                            return res.json(encryptResponse(r));
                        }
                    } else {
                        r.status = statusCodes.BAD_INPUT;
                        r.data = {
                            "message": "Receiver account number not in beneficiary list"
                        }
                        return res.json(encryptResponse(r));
                    }
                }).catch((err) => {
                    r.status = statusCodes.SERVER_ERROR;
                    r.data = {
                        "message": err.toString()
                    };
                    return res.json(encryptResponse(r));
                });
            } else {
                r.status = statusCodes.BAD_INPUT;
                r.data = {
                    "message": "Insufficient balance"
                };
                return res.json(encryptResponse(r));
            }
        } else {
            r.status = statusCodes.NOT_AUTHORIZED;
            r.data = {
                "message": "Not authorized"
            }
            return res.json(encryptResponse(r));
        }
    }).catch((err) => {
        r.status = statusCodes.SERVER_ERROR;
        r.data = {
            "message": err.toString()
        };
        res.json(encryptResponse(r));
    });
});

module.exports = router;