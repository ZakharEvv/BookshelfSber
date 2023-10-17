# BookshelfSber
["BookshelfSber"](https://bookshelfsber.onrender.com/swagger-ui.html) - это REST-сервис, созданный для удобного хранения информации о книжной полке. Этот сервис обеспечивает пользователей возможностью управлять книгами и авторами. 
Проект реализован с использованием Java и фреймворка Spring Boot. Для работы с данными используются такие библиотеки, как Hibernate для ORM (Object-Relational Mapping), PostgreSQL в качестве СУБД, Liquibase для управления миграциями базы данных, Swagger для документации и API-интерфейса, а также JUnit для тестирования.

База данных PostreSQL и сервис развернуты с помощью сервиса Render.

Попробовать можно [здесь](https://bookshelfsber.onrender.com/api/authors).

Изначально в БД уже добавлены авторы и книги с помощью Liquibase.

## Основные функции
### Авторы
- Получение всех авторов
- Добавление автора
- Получение автора по ID
- Изменение автора по ID
- Удаление автора по ID (При удалении автора все его книги удаляются)

### Книги
- Получение всех книг с возможностью фильтрации и сортировки
- Добавление книги
- Получение книги по ID
- Получение книги по ID автора
- Изменение книги по ID
- Изменение наличия книги на полке
- Удаление книги по ID

## Документация
Вы можете ознакомиться с документацией запросов Swagger [здесь](https://bookshelfsber.onrender.com/swagger-ui.html).

## Требования

Для запуска приложения локально вам понадобятся следующие инструменты:

- Intellij Idea: [Скачать Intellij Idea](https://www.jetbrains.com/ru-ru/idea/).
- Java 17: [Скачать Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (Или скачайте внутри среды разработки).

## Установка и Запуск

1. Клонируйте репозиторий на вашем компьютере:

   ```sh
   git clone https://github.com/ZakharEvv/BookshelfSber.git

2. Откройте проект в IDE Intellij Idea

3. Запустите BookshelfSber Application в Intellij Idea


