# Консольная игра "Шашки"

## Описание

Эта игра представляет собой консольную версию классической игры "Шашки". В игре участвуют два человека, играющих по очереди. Основные правила игры:

- Шашки не могут есть подряд.
- Шашки не могут есть назад.
- Дамки ходят и бьют фигуры как ферзь в шахматах (по диагонали на любое количество клеток).

![image](https://github.com/ssss1131/Checkers/assets/115891255/dfc3b9b8-2968-4b04-b7dd-174754a66948)


## Установка

1. Склонируйте репозиторий:
    ```bash
    git clone https://github.com/ssss1131/Checkers.git
    ```
2. Перейдите в директорию с проектом:
    ```bash
    cd ваш-репозиторий
    ```
3. Соберите проект с помощью вашей IDE или с помощью команды (если используется Maven):
    ```bash
    mvn clean install
    ```

## Запуск игры

1. Запустите основной класс проекта: Main.java

## Правила игры

- Игроки ходят по очереди.
- Обычные шашки ходят только вперёд по диагонали на одну клетку.
- Шашки не могут есть сразу несколько фигур.
- Шашки не могут есть назад.
- Дамки могут двигаться и есть фигуры как в шахматаx.

## Пример хода игры

Пример ходов, записанных в виде:
- A3 - B4
- B6 - C5

Все возможные ходы для выбранной фигуры подсвечены фиолетовым цветом
