# Указываем образ, от которого наследуемся
FROM php:7.4-cli

# Устанавливаем рабочую директорию
WORKDIR /project

# Копируем директорию из основной файловой системы внутрь образа
COPY ./project .

# Определяет действие, которое будет выполнено при старте контейнера (docker run)
# Используется только в случае, если контейнер был запущен без указания команды
# [команда, аргумент]
CMD ["java", "-jar", "build/libs/docker.jar"]

# Сообщаем, что контейнер использует 8000 порт
EXPOSE 8000
