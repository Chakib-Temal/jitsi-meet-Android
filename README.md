Source used: https://jitsi.org/  
Useful link: https://github.com/jitsi/jitsi-meet  


What you will need to install the server:  
1-Prepare a preferred domain name or otherwise an IP address  
2-Type of OS Recommended: Ubuntu 16.04 TLS / 18 .04 TLS  

Commands to use: I used a Ubuntu 16.04 server, the basic commands were not installed by defaults, so it might be that your server already contains default software and that it will tell you that it is already installed for some orders.

It is important to follow the steps one after the other especially for Apache2

# useful:
apt-get install apt-transport-https
sudo apt-get -y software-properties-common
sudo add-apt-repository ppa: webupd8team / java
sudo apt-get update
sudo apt install oracle-java8-set-default
sudo apt install apache2

# Security
apt-get -y install iptables
apt-get -y install ufw
ufw enable
ufw allow in ssh
ufw allow in 80 / tcp
ufw allow in 443 / tcp
ufw allow in 10000: 20000 / udp
ufw restart service

# Collect the keys :
wget https://download.jitsi.org/jitsi-key.gpg.keygpg jitsi-key.gpg.key
gpg --search-keys dev@jitsi.org
gpg --list-sigs dev@jitsi.org
gpg --recv-keys 8030357F
gpg --recv-keys C2EFE8AA
apt-key add jitsi-key.gpg.key
Choose the first key

# Addition of the Deposit:
echo 'deb https://download.jitsi.org stable /' >> /etc/apt/sources.list.d/jitsi-stable.list
apt-get update

# Installation:
apt-get -y install jitsi-meet
Use your domain name, and take the keys already ready

# Certificate :
cd / usr / share / jitsi-meet / scripts /
./install-letsencrypt-cert.sh
Give your e-mail address or that of the developer

You are ready to make calls on the exact domain name you gave during
the installation, call it in https: // domain_name
