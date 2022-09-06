# Private Notes

<div>-Приложение с функциональностью личного дневника; c аутентификацией и авторизацией пользователей (USER, ADMIN).</div>
<br>
<div>-Можно зарегистрироваться и создавать личные записи. Для каждой записи выводится дата создания и последнего обновления. 
Пароль пользователя хранится в БД в хешированном виде.</div>
<br>
<div>-Конфигурация: database PostgreSQL, JPA provider - Hibernate.</div>
<br>
-Можно выдать пользователю роль ADMIN, тогда у него появится возможность перейти на страницу /admin, где видно всех зарегистрированных пользователей.
<br>
<br>
<div>Скриншоты страниц: </div>
<img src="src/main/resources/static/auth_page.jpg" alt="auth_page">
<img src="src/main/resources/static/notes_page.jpg" alt="notes_page">
<img src="src/main/resources/static/note_page.jpg" alt="note_page">
<br>
<div>In order to run the application on your host, you need to create a file <i>hibernate.properties</i> in folder 
<i>/src/main/resources/</i> with data for connecting to your database (property file template - file <i>hibernate.properties.origin</i>
in the same directory)</div>

