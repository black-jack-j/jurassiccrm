cd backend

mvn clean verify -DskipTests=true -Dgenerate-api-client

cd ../frontend/src/main/ui

npm install

mkdir -p target

npx openapi-generator-cli generate -i ../resources/openapi.json -g typescript-fetch -o target --additional-properties=npmName=jurassiccrm-client-api

cd target

npm install

npm run build

mkdir -p ../src/generatedclient

mv -f dist/* ../src/generatedclient/*

cd ..

rm -rf ./target


