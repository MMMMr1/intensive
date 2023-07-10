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

Tехнологии:

- Java 11
- Postgresql

______________________________________________________________________________

Описание проекта:

Возможности:    регистрировать пациента, получить персональные данные пациентов:

  	 uuid, фамилия, имя, отчество, адрес, телефон, номер медицинской карты, время создания и обновления;
   
   	регистрировать врачей, получить их данные: 
   
   	uuid,  фамилия, имя, отчество, адрес, должность, отделение, время создания и обновления;
		
  		записать диагноз и лечение

Проект написан с использованием JDBC.
Транзакции для операций create, update, delete контролируются на уровне сервисов с помощью EntityTransaction.java.
Пул соединений организован с помощью библиотеки c3p0;

Для сборки проекта нужно внести данные о базе данных в файл application.properties и сохранить его в папке /conf apache-tomcat

_______________________________________________________________________________   

Зависимости:

servlet -  API, предназначенный для реализации на сервере и работе с клиентом по схеме запрос-ответ (JSON - формат)
c3p0 - библиотека, предоставляющая стандартные объекты jdbc DataSource

gson - библиотека для сериализации объектов Java в JSON и наоборот.

jackson - библиотека, которая конвертирует строки JSON и простые объекты Java

________________________________________________________________________________


Endpoints:

Создание пациента: http://localhost:8080/hospital-1.0/patients (POST)

![image](https://github.com/MMMMr1/intensive/assets/95496893/95890769-29a7-46cf-a63d-4d91a654e5d3)

Внесение изменений в данные пациента: http://localhost:8080/hospital-1.0/patients (PUT)

<img width="308" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/684ef0f6-ec54-4d2a-96bb-509566efa4e3">

Удаление пациента: http://localhost:8080/hospital-1.0/patients (DELETE)


Получение данных всех пациентов: http://localhost:8080/hospital-1.0/patients (GET)

_________________________________________________________________________________

Создание врача: http://localhost:8080/hospital-1.0/doctors (POST)

<img width="194" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/4d080003-7fa8-4643-a092-c6aa37777380">


Внесение изменений в данные врача: http://localhost:8080/hospital-1.0/doctors (PUT)

<img width="194" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/4d080003-7fa8-4643-a092-c6aa37777380">


Удаление врача: http://localhost:8080/hospital-1.0/doctors (DELETE)


Получение данных всех врачей: http://localhost:8080/hospital-1.0/doctors (GET)


_________________________________________________________________________________
Сервис истории болезни:

Создание карты истории болезни: http://localhost:8080/hospital-1.0/histories (POST)

<img width="347" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/0b742ace-e2bf-414e-b5a1-4a77c2084a0a">

Внесение изменение в карточку истории болезни: http://localhost:8080/hospital-1.0/histories (PUT)

<img width="334" alt="image" src="https://github.com/MMMMr1/intensive/assets/95496893/27ea9f46-5a87-448e-b8ff-d602773ee35b">

Удаление карточки истории болезни: http://localhost:8080/hospital-1.0/histories (DELETE)

Получение всех карт истории болезни: http://localhost:8080/hospital-1.0/histories (GET)
