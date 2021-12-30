# file-server

[![Kotlin](https://img.shields.io/badge/kotlin-1.6.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

## Installation

### 1. Download files

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
