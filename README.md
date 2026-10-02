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

export AWS_ACCESS_KEY_ID=SEU_ACCESS_KEY
export AWS_SECRET_ACCESS_KEY=SEU_SECRET_KEY
export AWS_SESSION_TOKEN=SEU_SESSION_TOKEN

3. Salvar e carreagar
source ~/.bashrc

echo $DB_HOST
deve retornar:

localhost
