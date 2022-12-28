TelegramBot Java Project 
===============================
Масштабируемый проект для расчета обмена валют разных банков

##Основная идея - это парсинг сайтов банков или получение данных из (условно) открытых API.
- Проект собран на интерфейсе TelegramLongPollingBot с использованием многослойной архитектуры и Spring Boot. 
- Хранение текущих данных организовано в  HashMap доступ к ним через  Singleton.
- Расширяемость обеспечивается фабриками для KeyBoards, Handlers, Banks
