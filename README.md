<h1>Project X</h1>
<h3> Запуск и настройка веб-сервера для добавления новых цитат</h3>
<p>
Должна быть установлена MongoDB<br />

Сервер работает на Mojolicious(Perl), который требуется установить. Для этого выполняем в терминале:
<ol>
	<li> Установливаем Mojolicious из cpan:<br /> 
	<code lang="sh"> sudo cpan -i Mojolicious</code> <br />
	Если это первый запуск cpan, то он предложит его настроить. Выбираем настройки по умолчанию(жмём Enter).
	</li>
	<li>
		Устанавливаем драйвер MongoDB для Perl(ставится довольно долго, можно сходить попить чаю =) )<br />
		<code lang="sh"> sudo cpan -i MongoDB</code>
	</li>
</ol>
Затем переходим в папку server  и запускаем сервер: <code lang="sh"> morbo server.pl daemon</code> <br />
По умолчанию сервер слушает http://127.0.0.1:3000<br />
Если необходимо добавить свои настройки подключения к базе данных, то после server.pl вызывается флаг dbconnect, которому передается строка подключения. <br/>
Например: <code lang="sh"> morbo server.pl dbconnect mongodb://localhost:27017 daemon</code>
</p>
