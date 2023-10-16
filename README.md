# BookshelfSber
"BookshelfSber" - это REST-сервис, созданный для удобного хранения информации о книжной полке. Этот сервис обеспечивает пользователей возможностью управлять книгами и авторами. 
Проект реализован с использованием Java и фреймворка Spring Boot. Для работы с данными используются такие библиотеки, как Hibernate для ORM (Object-Relational Mapping), PostgreSQL в качестве СУБД, Liquibase для управления миграциями базы данных, Swagger для документации и API-интерфейса, а также JUnit для тестирования.
## Требования

Для запуска приложения вам понадобятся следующие инструменты:

- Docker: [Скачать Docker](https://www.docker.com/get-started)
- Intellij Idea: [Скачать Intellij Idea](https://www.jetbrains.com/ru-ru/idea/)
- Java 17: [Скачать Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (Или скачайте внутри среды разработки)

## Установка и Запуск

1. Установите Docker, если у вас его еще нет. Вы можете скачать Docker с [официального сайта](https://www.docker.com/get-started).

2. Клонируйте репозиторий на вашем компьютере:

   ```sh
   git clone https://github.com/ZakharEvv/BookshelfSber.git

3. Откройте проект в IDE Intellij Idea
   
5. Запустите контейнер PostgreSQL с помощью следующей команды:

   ```sh
   docker run --name bookshelf -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=password -p 5433:5432 -d postgres

6. Теперь вы можете запустить BookshelfSber Application в Intellij Idea

## Основные функции
### Получение всех авторов
### Добавление автора
### Получение автора по ID
### Изменение автора по ID
### Удаление автора по ID

### Получение всех книг с возможностью фильтрации и сортировки
### Добавление книги
### Получение книги по ID
### Получение книги по ID автора
### Изменение книги по ID
### Изменение наличия книги на полке
### Удаление книги по ID
