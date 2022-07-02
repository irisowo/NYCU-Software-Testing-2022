## Note

```bash
cd Lab_6/distribute

sudo docker build -t st-lab6 . —no-cache

sudo docker run -v $PWD/share:/home/lab6/share -it st-lab6 /bin/bash

cd /home/lab6/share
```