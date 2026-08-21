## TELEGRAM TOXIC BOT

### Описание
Бот в телеграме, который грубит и отвечает на сообщения "да", "нет", "где" и другие.

### Требования
 - Java 21+
 - SQL база данных (Postgres, MySQL и др)

### Подготовка к запуску

1. Установить Java 21+ и SQL базу данных

2. Создать таблицы в БД

```sql
create table telegram_user (
	id bigserial not null,
	telegram_id int8 not null,
	user_data jsonb not null,
	created_at timestamptz default now() null,
	"version" int4 default 1 not null,
	is_current bool default true null,
	user_hash int4 null,
	constraint telegram_user_id_not_null not null id,
	constraint telegram_user_pkey primary key (id),
	constraint telegram_user_telegram_id_not_null not null telegram_id,
	constraint telegram_user_telegram_id_version_key unique (telegram_id, version),
	constraint telegram_user_user_data_not_null not null user_data,
	constraint telegram_user_version_not_null not null version
);

create unique index idx_unique_current_user on telegram_user
    using btree (telegram_id)
where
(is_current = true);
```

```sql
create table telegram_update (
	id bigserial not null,
	"type" varchar null,
	sub_type varchar null,
	telegram_id int8 not null,
	update_data jsonb not null,
	created_at timestamptz null,
	"timestamp" timestamptz default now() null,
	constraint telegram_update_id_not_null not null id,
	constraint telegram_update_pkey primary key (id),
	constraint telegram_update_telegram_id_not_null not null telegram_id,
	constraint telegram_update_update_data_not_null not null update_data
);
```

3. Скомпилировать программу из корня проекта командой `./mvnw clean package`. Скомпилированный файл программы можно найти в директории `data/target/telegram-toxic-bot-data-X.X.X.jar`

4. Создать отдельную директорию для программы и поместить туда скомпилированный файл.

5. В директории с приложением создать в корне и заполнить конфигурационный файл `application.yaml`:
```yaml
server:
  port: ${server_port}

spring:
  output:
    ansi:
      enabled: detect
  datasource:
    password: ${db_password}
    username: ${db_username}
    url: ${db_url}
  application:
    name: telegram-toxic-bot-app


management:
  info:
    build:
      enabled: ${info_build_enabled}


logging:
  file:
    path: ${log_file_path}
    name: ${log_file_name}
  charset:
    console: UTF-8
  logback:
    rollingpolicy:
      max-file-size: ${logback_max_file_size}
      max-history: ${logback_max_history}
      total-size-cap: ${logback_total_size_cap}
  level:
    root: ${log_level_root}
    ru.thevalidator.cyberdictatorbot: ${log_level_base_package}


bot:
  api-key: ${bot_token}
  bot-id: ${bot_id}
  bot-name: ${bot_name}


reply-phrases:
  aah-reply: ${aah_reply_file_path}
  abusive-names: ${abusive_names}
  bot-mention-reply: ${bot_mention_reply}
  where-reply: ${where_reply_file_path}
  yes-reply: ${yes_reply_file_path}
  three-hundred-reply: ${three_hundred_reply_file_path}
  no-reply: ${no_reply_file_path}
  short-no-reply: ${short_no_reply_file_path}
  reply-to-bot-reply: ${reply_to_bot_reply}


notification:
  users:
    - ${user1_id}

```

| Параметр | Описание | Значение по умолчанию/Пример |
|----------|----------|------------------------------|
| `server.port` | Порт, на котором запускается приложение | `8080` |
| `spring.output.ansi.enabled` | Включение ANSI-цветов в выводе консоли (always, never, detect — автоматическое определение) | `detect` |
| `spring.datasource.password` | Пароль для подключения к базе данных | `db_password` |
| `spring.datasource.username` | Имя пользователя для подключения к БД | `db_username` |
| `spring.datasource.url` | URL-адрес подключения к базе данных | `localhost:5432` |
| `spring.application.name` | Имя приложения | `telegram-toxic-bot-app` |
| `management.info.build.enabled` | Включение информации о сборке в /actuator/info | `true` |
| `logging.file.path` | Директория для сохранения лог-файлов | `log` |
| `logging.file.name` | Имя файла лога | `log/bot.log` |
| `logging.charset.console` | Кодировка для вывода логов в консоль | `UTF-8` |
| `logging.logback.rollingpolicy.max-file-size` | Максимальный размер одного файла лога перед ротацией (5000000, 5000KB, 5MB and 2GB) | `100MB` |
| `logging.logback.rollingpolicy.max-history` | Количество хранимых архивных лог-файлов | `30` |
| `logging.logback.rollingpolicy.total-size-cap` | Общий лимит размера всех лог-файлов (5242880, 5120KB, 5120 KB, 5MB, 5 MB, 2 GB ) | `1GB ` |
| `logging.level.root` | Уровень логирования для корневого логгера (например, INFO, DEBUG, WARN, ERROR) | `INFO` |
| `logging.level.ru.thevalidator.telegramtoxicbot` | Уровень логирования для пакета приложения | `INFO` |
| `bot.api-key` | Токен Telegram-бота (API ключ) | `bot_token` |
| `bot.bot-id` | Идентификатор Telegram-бота | `bot_id` |
| `bot.bot-name` | Имя Telegram-бота (username) | `bot_name` |
| `reply-phrases.aah-reply` | Путь к файлу с ответами на "ааа" | `file:replies/aah-reply.txt` |
| `reply-phrases.abusive-names` | Список оскорбительных имен/слов | `file:replies/abusive-names.txt` |
| `reply-phrases.bot-mention-reply` | Ответ при упоминании бота | `file:replies/bot-mention-reply.txt` |
| `reply-phrases.where-reply` | Путь к файлу с ответами на "где" | `file:replies/where-reply.txt` |
| `reply-phrases.yes-reply` | Путь к файлу с ответами на "да" | `file:replies/yes-reply.txt` |
| `reply-phrases.three-hundred-reply` | Путь к файлу с ответами на "300" | `"file:replies/300-reply.txt` |
| `reply-phrases.no-reply` | Путь к файлу с ответами на "нет" | `file:replies/no-reply.txt` |
| `reply-phrases.short-no-reply` | Путь к файлу с ответами на "не" | `file:replies/short-no-reply.txt` |
| `reply-phrases.reply-to-bot-reply` | Ответ, когда кто-то отвечает на сообщение бота | `file:replies.reply-to-bot-reply.txt` |
| `notification.users` | Список ID пользователей, которые получают уведомления | `user_id` (может быть несколько) |

**Примечание:**
- В `notification.users` указан массив, поэтому можно добавить несколько ID пользователей:
```yaml
notification:
  users:
    - user_id_1
    - user_id_2
    - user_id_3
```
6. Создать и заполнить файлы с ответами бота, указанные в конфиге для каждого из параметров:
- aah-reply
- abusive-names
- bot-mention-reply
- where-reply
- yes-reply
- three-hundred-reply
- no-reply
- short-no-reply
- reply-to-bot-reply

В файлах одна строка - один ответ.

### Запуск

Запуск осуществляется из директории с приложением командой в консоли: `java -jar telegram-toxic-bot-data-X.X.X.jar`
