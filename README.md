## Проект по автоматизации тестирования API
### Веб сайт <a target="_blank" href="https://restful-booker.herokuapp.com/">restful-booker</a>

<p align="center">
<img title="Тинькофф Город" src="images/screens/cover.jpg">
</p>


## :rocket: Содержание:

- Технологии и инструменты
- Список методов, на которые были реализованы тесты
- Запуск тестов (сборка в Jenkins)
- Allure-отчет
- Интеграция с Allure TestOps
- Уведомление в Telegram о результатах прогона тестов

## :rocket: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://qameta.io/"><img src="images/logo/Allure_TO.svg" width="50" height="50"  alt="Allure TestOps"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
</p>

## :rocket:  Список методов

- [x] CreateBooking - создание нового бронирования
- [x] GetBooking - получение информации по бронированию
- [x] UpdateBooking - обновление информации по бронированию
- [x] DeleteBooking - удаление информации по бронированию

###  Локальный запуск :
Пример командной строки:
```bash
gradle clean test  
```

Получение отчёта:
```bash
allure serve build/allure-results
```

###  :rocket:  Удаленный запуск (в Jenkins):
1. Открыть <a target="_blank" href="https://jenkins.autotests.cloud/job/restful-booker-api///">проект</a> и нажать **Собрать**

![This is an image](/images/screens/jenkins.jpg)


###  Отчет в Allure report

![This is an image](/images/screens/allureReport.jpg)

**Статус прогона тестов в диаграммах**
![This is an image](/images/screens/Graph.jpg)

**Тесты**

<p align="center">
<img title="Allure Tests" src="images/screens/reports.jpg">
</p>

## :rocket: Интеграция с Allure TestOps

![This is an image](/images/screens/allureCases.jpg)

![This is an image](/images/screens/allureBoard.jpg)

## :rocket: Получение отчетов о прохождении тестов в telegram

![This is an image](/images/screens/tg.jpg)
