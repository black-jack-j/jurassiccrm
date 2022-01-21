cd backend

mvn clean verify -DskipTests=true

cd ../frontend

cd src/main/ui

npm install

mkdir -p target

npx openapi-generator-cli generate -i ../resources/openapi.json -g typescript-fetch -o target --additional-properties=npmName=jurassiccrm-client-api

cd target

npm install

npm build

npm pack

cd ..

npm install ./target/jurassiccrm-client-api-0.0.1.tgz

