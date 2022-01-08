# file-server

[![Kotlin](https://img.shields.io/badge/kotlin-1.6.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![GitHub Releases](https://img.shields.io/github/v/release/sya-ri/file-server)](https://github.com/sya-ri/file-server/releases)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)

## Features

- Easy startup
- Support WebDAV
- Support compress (gzip, deflate)

## Installation

### 1. Download a file

https://github.com/sya-ri/file-server/releases

### 2. Setup config.properties

Rename `config.sample.properties` to `config.properties`.

```properties
# Web server port
# - default: 8080
Port=8080

# File download source
# - Local
# - WebDAV
FileProvider=Local

# Root folder to get files
# - Used when FileProvider is Local
# - default: ./
FileProvider_Local_Root=./

# WebDAV url
# - Used when FileProvider is WebDAV
FileProvider_WebDAV_Url=

# WebDAV userName
# - Used when FileProvider is WebDAV
FileProvider_WebDAV_UserName=

# WebDAV password
# - Used when FileProvider is WebDAV
FileProvider_WebDAV_Password=
```

#### File download source

- Local
- WebDAV (via Ktor client)

## Usage

To download files.

```shell
curl -O --compressed <URL>
```
