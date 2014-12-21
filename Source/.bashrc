# .bashrc

# Source global definitions
if [ -f /usr/socs/Profile ]; then
        . /usr/socs/Profile
fi

# User specific aliases and functions
./Source/backup
cd 
alias lsa='ls -al'
export PS1="[\u][\h][\w] Ad Lucem: "

