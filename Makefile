build:
	docker build -t ppbot:v0.2 .
	docker run -p 8081:8080 ppbot:v0.2