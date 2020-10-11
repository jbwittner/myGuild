# -*- mode: ruby -*-
# vi: set ft=ruby :


Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/bionic64"
  config.vm.provider "virtualbox" do |v|
  v.memory = 2048
  v.cpus = 1
  v.name = "Ubuntu_myGuild"
  end

  config.vm.box_check_update = false

  config.vm.network "forwarded_port", guest: 3306, host: 3306
  config.vm.network "forwarded_port", guest: 8080, host: 8080
  config.vm.network "forwarded_port", guest: 80, host: 80
  config.vm.network "forwarded_port", guest: 5432, host: 5432

  config.vm.provision "shell", inline: <<-SHELL
    apt-get update
  SHELL
  
  config.vm.provision :docker
  config.vm.provision :docker_compose

  config.vm.provision "file", source: "sql/init_tables.sql", destination: "init_tables.sql"
  config.vm.provision "file", source: "sql/add_data_dev.sql", destination: "add_data_dev.sql"
  config.vm.provision "file", source: "waitForMySQL.sh", destination: "waitForMySQL.sh"

  config.vm.provision "shell", inline: <<-SHELL 
    source ~/.profile && [ -z "$SQL_USER" ] && echo "export SQL_USER=myGuild_user" >> ~/.profile
    source ~/.profile && [ -z "$SQL_PASSWORD" ] && echo "export SQL_PASSWORD=MyGuildPass2020" >> ~/.profile
    source ~/.profile && [ -z "$SQL_DB" ] && echo "export SQL_DB=myGuild_db" >> ~/.profile
    apt-get install -y mysql-client dos2unix
    chmod +x waitForMySQL.sh
    dos2unix waitForMySQL.sh
  SHELL

# Install Docker Compose once on the Ubuntu VM
  config.vm.provision :docker_compose, yml: "/vagrant/docker-compose.yml", run: "always"

  config.vm.provision "shell", inline: <<-SHELL
    echo SQL_USER = $SQL_USER
    echo SQL_PASSWORD = $SQL_PASSWORD
    echo SQL_DB = $SQL_DB
    echo Start waiting
    sh waitForMySQL.sh
    echo Start init tables
    docker exec -i vagrant_mysql_1 mysql -u"$SQL_USER" -p"$SQL_PASSWORD" "$SQL_DB" < init_tables.sql
    echo Start add datas
    docker exec -i vagrant_mysql_1 mysql -u"$SQL_USER" -p"$SQL_PASSWORD" "$SQL_DB" < add_data_dev.sql
  SHELL
  
end
