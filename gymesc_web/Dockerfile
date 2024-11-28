FROM node:alpine

WORKDIR /usr/src/app

COPY package*.json ./

RUN if ! [ -x "$(command -v ng)" ]; then npm install -g @angular/cli; fi

RUN if [ ! -d "node_modules" ]; then npm install; fi

COPY . .

CMD ["ng", "serve", "--host", "0.0.0.0"]
