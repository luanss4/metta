release: npm --prefix ./frontend install && npm --prefix ./frontend run build && xcopy /E /I ./frontend/dist ./backend/src/main/resources/static
web: java -Dserver.port=$PORT -jar backend/target/*.jar