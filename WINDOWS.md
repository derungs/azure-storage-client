# Getting Started - Windows

## Install

From Powershell:

### AZ CLI

```
Invoke-WebRequest -Uri https://aka.ms/installazurecliwindows -OutFile .\AzureCLI.msi; Start-Process msiexec.exe -Wait -ArgumentList '/I AzureCLI.msi'; rm .\AzureCLI.msi
```

### Java
TODO

### Clojure

```
iwr -useb download.clojure.org/install/win-install-1.10.1.536.ps1 | iex
```
