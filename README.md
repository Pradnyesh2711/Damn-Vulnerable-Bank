<h1 align="center">Damn Vulnerable Bank</h1>

<p align="center">
  <a href="https://github.com/rewanthtammana/Damn-Vulnerable-Bank/fork">
    <img src="https://img.shields.io/github/forks/rewanthtammana/Damn-Vulnerable-Bank">
  </a>
  <a href="https://github.com/rewanthtammana/Damn-Vulnerable-Bank/stargazers">
    <img src="https://img.shields.io/github/stars/rewanthtammana/Damn-Vulnerable-Bank">
  </a>
  <a href="https://github.com/rewanthtammana/Damn-Vulnerable-Bank/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/rewanthtammana/Damn-Vulnerable-Bank">
  </a>
  <a href="https://twitter.com/intent/tweet?text=Damn%20Vulnerable%20Bank%20Guide:&url=https%3A%2F%2Fgithub.com%2Frewanthtammana%2FDamn-Vulnerable-Bank">
    <img src="https://img.shields.io/twitter/url?url=https%3A%2F%2Fgithub.com%2Frewanthtammana%2FDamn-Vulnerable-Bank">
  </a>
</p>

<p align="center">
  <b>Guide: https://rewanthtammana.com/damn-vulnerable-bank/</b>
</p>

## About application
[Damn Vulnerable Bank](https://github.com/rewanthtammana/Damn-Vulnerable-Bank) is designed to be an intentionally vulnerable android application. All the details are documented in the guide, [here](https://rewanthtammana.com/damn-vulnerable-bank/).

![Guide overview](./images/damn-vulnerable-bank-guide.png)

<!-- <img src="https://github.com/rewanthtammana/Damn-Vulnerable-Bank/blob/master/images/screen1.jpg" align="centre" height="600" width="395"><img src="https://github.com/rewanthtammana/Damn-Vulnerable-Bank/blob/master/images/screen2.jpg" align="centre" height="600" width="395"> -->

## Upcoming Sessions

### NoNameCon

* [https://cfp.nonamecon.org/nnc2021/talk/WCKLTN/](https://cfp.nonamecon.org/nnc2021/talk/WCKLTN/)

### Black Hat Europe

* [TBD](https://www.blackhat.com/eu-21/)

## Features
- [x] Sign up
- [x] Login
- [x] My profile interface
- [x] Change password
- [x] Settings interface to update backend URL
- [x] Add fingerprint check before transferring/viewing funds
- [x] Add pin check before transferring/viewing funds
- [x] View balance
- [x] Transfer money
  - [x] Via manual entry
  - [ ] Via QR scan
- [x] Add beneficiary
- [x] Delete beneficiary
- [x] View beneficiary
- [x] View transactions history
- [ ] Download transactions history

## List of vulnerabilities in the application

To keep things crisp and interesting, we hidden this section. Do not toggle this button if you want a fun and challenging experience. Try to explore the application, find all the possible vulnerabilities and then cross check your findings with this list.

<details>
  <summary>Spoiler Alert</summary>

- [x] Root and emulator detection
- [x] Anti-debugging checks (prevents hooking with frida, jdb, etc)
- [ ] SSL pinning - pin the certificate/public key
- [x] Obfuscate the entire code
- [x] Encrypt all requests and responses
- [x] Hardcoded sensitive information
- [x] Logcat leakage
- [ ] Insecure storage (saved credit card numbers maybe)
- [x] Exported activities
- [ ] JWT token
- [x] Webview integration
- [x] Deep links
- [ ] IDOR
</details>

## Backend to-do

- [x] Add profile and change-password routes
- [ ] Create different secrets for admin and other users
- [ ] Add dynamic generation of secrets to verify JWT tokens
- [ ] Introduce bug in jwt verification
- [x] Find a way to store database and mount it while using docker
- [X] Dockerize environment

## Core Team

[Damn Vulnerable Bank](https://rewanthtammana.com/damn-vulnerable-bank/) was created by 

|   |   |   |
|---|---|---|
| Rewanth Tammana (Rest API)  | [Github](https://github.com/rewanthtammana/)  | [LinkedIn](https://www.linkedin.com/in/rewanthtammana/)  |
| Akshansh Jaiswal (Android App)  | [Github](https://github.com/jaiswalakshansh)  | [LinkedIn](https://www.linkedin.com/in/akshanshjaiswal/)  |
| Hrushikesh Kakade (Android App)  | [Github](https://github.com/HrushikeshK/)  | [LinkedIn](https://www.linkedin.com/in/hrushikeshkakade/)  |


Read more, [here](https://rewanthtammana.com/damn-vulnerable-bank/authors.html).

## Contributors

<a href = "https://github.com/rewanthtammana/damn-vulnerable-bank/contributors">
  <img src = "https://contrib.rocks/image?repo=rewanthtammana/damn-vulnerable-bank"/>
</a>
