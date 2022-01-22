@echo off
pushd .
cd backend

call mvn clean verify -DskipTests=true -Dgenerate-api-client

cd ..\frontend\src\main\ui

@echo on

call npm install

call npx openapi-generator-cli generate -i ../resources/openapi.json -g typescript-fetch -o target --additional-properties=npmName=jurassiccrm-client-api

cd target

call npm install

call npm run build

if not exist "..\src\generatedclient\" md ..\src\generatedclient\

xcopy .\dist\* ..\src\generatedclient\ /e /y

cd ..

rmdir /s /q target

popd