# Каталог недвижимости. Тестовое задание #
## Данные для входа в приложение ##
- Логин: Юрий
- Пароль: 123456

## Плюсы текущей реализации ##
1. В БД не хранятся пароли, только хеши
2. При создании новой записи выполняется проверка на допустимость заполнения полей (пустые поля и нулевые значения не принимаются)
3. Реализован класс, расширяющий класс CursorWrapper. Позволяет проще получать данные из БД, избавится от дублирования кода.
4. Реализована обработка не верных данных при входе в приложение
5. При повторном входе не требуется вводить данные снова, если не была нажата кнопка выхода
6. Для полей с ценами используется форматирование для лучшей читаемости



## Минусы текущей реализации ##
1. Записи из базы данных не удаляются, возможно только добавление
2. В базе данных не используются индексы, более низкая скорость работы
3. Не предусмотрено сетевого взаимодействия. Нет возможности синхронизировать данные в программе с внешней базой
4. У записей не указана дата добавления. Нет сортировки по дате
5. Не предусмотрена возможность создания фотографии, либо выбора изображения из памяти устройства
6. Нет поля с указанием типа недвижимости: новостройка, вторичная, коммерческое, аренда
7. Нет возможности отправить информацию на электронную почту, либо мессенджер.
8. Не предусмотрено описание объекта
9. В графическом интерфейсе не предусмотрено создание нового пользователя
10. Не выполнено покрытие тестами
11. Не используется React Native









