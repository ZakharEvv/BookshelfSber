# BookshelfSber
"BookshelfSber" - это REST-сервис, созданный для удобного хранения информации о книжной полке. Этот сервис обеспечивает пользователей возможностью управлять книгами и авторами. 
Проект реализован с использованием Java и фреймворка Spring Boot. Для работы с данными используются такие библиотеки, как Hibernate для ORM (Object-Relational Mapping), PostgreSQL в качестве СУБД, Liquibase для управления миграциями базы данных, Swagger для документации и API-интерфейса, а также JUnit для тестирования.
## Требования

Для запуска приложения вам понадобятся следующие инструменты:

- Docker: [Скачать Docker](https://www.docker.com/get-started)
- Intellij Idea: [Скачать Intellij Idea](https://www.jetbrains.com/ru-ru/idea/)

## Установка и Запуск

1. Установите Docker, если у вас его еще нет. Вы можете скачать Docker с [официального сайта](https://www.docker.com/get-started).

2. Клонируйте репозиторий на вашем компьютере:

   ```sh
   git clone https://github.com/ZakharEvv/BookshelfSber.git

3. Откройте проект в IDE Intellij Idea
4. Запустите контейнер PostgreSQL с помощью следующей команды:
   
   docker run --name bookshelf -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5433:5432 -d postgres

5. Теперь вы можете запустить BookshelfSber Application в Intellij Idea
