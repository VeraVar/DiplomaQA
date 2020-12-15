#  Описание процедуры запуска авто-тестов

[План автоматизации тестирования](https://github.com/VeraVar/DiplomaQA/blob/master/Documentation/Plan.md)
[Отчёт о проведённом тестировании](https://github.com/VeraVar/DiplomaQA/blob/master/Documentation/Report.md)
[Отчёт о проведённой автоматизации](https://github.com/VeraVar/DiplomaQA/blob/master/Documentation/Summary.md)

## Необходимое ПО:
1.	IntelliJ IDEA [(ссылка для установки)](https://www.jetbrains.com/idea/download/#section=windows).
1.	Docker Desktop или Docker Toolbox в зависимости от операционной системы [(ссылка для установки)](https://www.docker.com/get-started).

## Предусловия:
1. Скачать репозиторий по [ссылке](https://github.com/VeraVar/DiplomaQA.git).
1. Открыть проект в IntelliJ IDEA.
1. Запустить Docker Desktop или Docker Toolbox.

## Настройка конфигурации:
1. Ввести в терминале команду для запуска контейнеров с базами данных MySQL и PostgreSQL и тестируемым приложением на NodeJS:
```
docker-compose up
```
2. В новой вкладке терминала ввести команду для запуска тестируемого приложения в зависимости от БД:
- `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar SUT/aqa-shop.jar` (для MySQL)
  
или
- `java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar SUT/aqa-shop.jar` (для PostgreSQL)

## Запуск тестов
В новой вкладке терминала ввести команду для запуска авто-тестов в зависимости от запущенной ранее БД:
- `gradlew clean test -Ddb.url=jdbc:mysql://localhost:3306/app` (для MySQL)
  
или
- `gradlew clean test -Ddb.url=jdbc:postgresql://localhost:5432/app` (для PostgreSQL)

## Просмотр отчёта
В новой вкладке терминала ввести команду:
```
gradlew allureServe
```
Отчёт откроется в браузере автоматически.

## Перезапуск приложения, тестов и/или отчёта
Для перезапуска приложения, тестов и/или отчёта (например, для использования другой БД) необходимо выполнить остановку их работы, нажав в соответствующих вкладках терминала Ctrl+С

## Завершение работы
Остановить контейнеры командой `docker-compose down`