# String Changer<br>
This tool allows you to scan and change specific strings in a directory.

# Accepted File Types<br>
* JSON
* YML / YAML
* TOML
* TXT

# Config Example<br>
```json
{
  "baseDirectory": "D:",
  "includedDirectories": [
    "<base>\\plugins"
  ],
  "excludedDirectories": [
    "<base>\\plugins\\CloudCrates"
  ],
  "replaceable": {
    "%%__NAME__%%": "Chubbyduck1"
  }
}
```

# How To Use<br>
```java
/* The file is the config json - It will automatically generate*/
new StringChanger(File) 
```
