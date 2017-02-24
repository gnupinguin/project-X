<h1>Project X</h1>
<h3> Запуск и настройка проекта для добавления новых цитат</h3>

<p>
	Для корректной работы приложения должны быть установлены, настроены и запущены:
	<ul>
		<li> MongoDB </li>
		<li> Apache Kafka</li>
	</ul>
	
	<p> <h3>Настройка MongoDB</h3> <br />
		В данной версии приложения MongoDB жёстко связана с приложением. Подключение к базе происходит по адресу 			<a>localhost:27017</a>. База Данных должна нызываться QuotesDB, содержать коллекцию quotes c полями  quoteText  и  quoteAuthor.  В будущем планируется добавить более гибкую настройку базы данных. 
	<p/>
	
	<p> <h3>Настройка  Apache Kafka</h3> <br />
		 Для работы   приложения необходимо, чтобы на сервере  Kafka  были созданы следующие топики:  quote-local, 			quote-replica.  Для корректной работы приложения необходимо корректно настроить файлы из папки 					Project-X/Server/src/main/kafka-conf/ . 

	</p>
	
</p>
