# alert-component


adiciona as variáveis de ambiente no servidor linux via shell

1.
nano ~/.bashrc

2.
export DB_HOST="localhost"
export DB_PORT="3306"
export DB_NAME="my_database"
export DB_USER="root"
export DB_PASSWORD="sua_senha"

3. Salvar e carreagar
source ~/.bashrc

echo $DB_HOST
deve retornar:

localhost
