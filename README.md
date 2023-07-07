1  Create CustomArrayList (extends AbstractList) with methods:
 - add(int index, E element)
 - addAll(Collection<? extends E> c)
 - clear()
 - get(int index)
 - isEmpty()
 - remove(int index)
 - remove(Object o)
 - sort(Comparator<? super E> c) - QuickSort


2. Сервис

   Предметная область : госпиталь

   Композиция:

   Пациент - >  история болезни (связь один ко многим)

   Доктор - >  истроия болезни (связь один ко многим)
   
<img width="138" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/4720b54c-9d57-434f-88ce-f66b1026c4a3">

_______________________________________________________________________________

 технологии:

 - Java 11
 - Postgresql

______________________________________________________________________________

Описание проекта:

   Возможности: 
   регистрировать пациента, получить персональные данные пациентов:
   
   uuid, фамилия, имя, отчество, адрес, телефон, номер медицинской карты, время создания и обновления;
   
   регистрировать врачей, получить их данные: 
   
   uuid,  фамилия, имя, отчество, адрес, должность, отделение, время создания и обновления;

   записать диагноз и лечение

  Проект написан с использованием JDBC. Транзакции для операций create, update, delete контролируются на уровне сервисов с помощью EntityTransaction.java. Пул соединений организован с помощью библиотеки c3p0;

  Для сборки проекта нужно внести данные о базе данных в файл application.properties и сохранить его в папке /conf apache-tomcat

_____________________________________________________________________________    

   Зависимости:
   
   servlet -  API, предназначенный для реализации на сервере и работе с клиентом по схеме запрос-ответ (JSON - формат)
   c3p0 - библиотека, предоставляющая стандартные объекты jdbc DataSource
   
   gson - библиотека для сериализации объектов Java в JSON и наоборот.
   
   jackson - библиотека, которая конвертирует строки JSON и простые объекты Java
   
________________________________________________________________________________
   

Endpoints: 

Создание пациента:

![image](https://github.com/MMMMr1/intensive/assets/95496893/beb5ce78-df3c-40b2-b068-2125829855d5)
![image](https://github.com/MMMMr1/intensive/assets/95496893/95890769-29a7-46cf-a63d-4d91a654e5d3)

Внесение изменений в данные пациента:

<img width="310" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/27ab5b38-0719-4b0e-a016-0762a8dd68c6">
<img width="308" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/684ef0f6-ec54-4d2a-96bb-509566efa4e3">

Удаление пациента:

![image](https://github.com/MMMMr1/intensive/assets/95496893/16dabb2e-8154-47b2-874c-fb18a6409425)

Получение данных всех пациентов:

<img width="307" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/a86391bb-47cb-4d97-8dea-54f7416f482e">

_____________________________________________________________________________

Создание врача:

<img width="314" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/a35bd60b-e920-48a0-883a-17219792a6b9">
<img width="194" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/4d080003-7fa8-4643-a092-c6aa37777380">
 

Внесение изменений в данные врача:

<img width="302" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/b2b5e699-1f96-47c4-93ee-b2c8748b920c">
<img width="194" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/4d080003-7fa8-4643-a092-c6aa37777380">
 

Удаление врача:

<img width="532" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/27c6f707-fdc0-4ff2-97e9-1889ce2eb58c">
 
Получение данных всех врачей:

![image](https://github.com/MMMMr1/intensive/assets/95496893/61734d53-4880-4c4f-bf8f-9e1f5a06add8)

Сервис истории болезни:

Создание карты истории болезни:

![image](https://github.com/MMMMr1/intensive/assets/95496893/19593231-a46e-47ac-9c42-d3e503502e17)
<img width="347" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/0b742ace-e2bf-414e-b5a1-4a77c2084a0a">

Внесение изменение в карточку истории болезни:

<img width="331" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/6a43b66d-7dde-4fa5-8478-268cb0cbd937">
<img width="334" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/27ea9f46-5a87-448e-b8ff-d602773ee35b">



Получение всех карт истории болезни:

![image](https://github.com/MMMMr1/intensive/assets/95496893/3a45e4a1-8d52-40eb-8d0d-5fada6c5d5ac)








   
