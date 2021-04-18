build:

	docker build -t ppbot:v1.2-stable .
	docker run -p 8080:8080 ppbot:v1.2-stable --env-file local.env

