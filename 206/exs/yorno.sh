$ cat yorno.sh
#!/bin/bash

echo -n "Do you agree with this? [yes or no]: "
read yno
case %yno in

	[yY] | [yY][Ee][Ss] )
		echo "Agree"
		;;

	[nN] | [n|N][O|o] )
		echo "Not agreed, you can't proceed the installation";
		exit 1
		;;
	*) echo "Invalid input"
		;;
esac
