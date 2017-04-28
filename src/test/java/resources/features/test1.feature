#language: ru

Функционал: Расписание занятий ОмГТУ

  Предыстория:
    Дано пользователь переходит по заданной странице

  @SelectFaculty
  Сценарий: SelectFaculty Проверка отображения на странице выбора факультета
    И открывается страница "Главная страница"
    Тогда пользователь (нажимает на ссылку)
    И открывается страница "Расписание занятий"
    Тогда пользователь (проверяет отображение элемента) "Выбор факультета"


#  @TravelInsurance
#  Сценарий: Тест 1
#    Когда

#  @PrivateCustomers
#  Scenario: Тест 2
#  Given Go to link
#  Given Check Date
#  Given Check Converter
#  Given Set on Converter RUB and EUR
#  Given check value of RUB to EUR
#  Given Set on Converter USD and EUR
#  Given check value of USD to EUR
#  Given Set on Converter USD and USD
#  Given check value of USD to USD
#
#  @Atm
#  Scenario: Тест 3
#  Given Go to link
#  Given check Green Icons
#  Given check Minimal count Location
#  Given check Order Locations
#  Given pick checkbox Payment Device
#  Given push button Show More
#  Given remove checkbox Offices
#
#  @DNS
#  Scenario: Тест ДНС
#  Given Go to link
#  Given pick Criteria
#  Given push Button
#  Given check Name
#  Given check Desc
#  Given find Average Good
#  Given find Cheap Goods with Waiting
#
