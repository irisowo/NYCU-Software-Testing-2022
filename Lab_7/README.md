# Lab 7: Fuzz Testing
1. We provide a small program that converts bmp from color to grayscale.
	- Use **AFL++** to find the file that can trigger the vulnerability.
	- Use **test.bmp** as init seed.
2. Deliverables shall include the following:
	- PoC: the file that can trigger the vulnerability
	- Screenshot of AFL++ running (with triggered crash): STUDENT_ID.png

**Do not compress the files and plagiarism!**

---

# Note

## Env
```bash
cd Lab_7/
docker pull aflplusplus/aflplusplus 
cd AFLplusplus 
make all
make install
```

## Run docker
```bash
docker run -ti -v [location of src code]:/code aflplusplus/aflplusplus
docker start -i [container_id]
```

## Usage
```bash
cd /code
Export CC=~/AFLplusplus/afl-cc 
Export AFL_USE_ASAN=1
make
mkdir in && cp test.bmp in/
/AFLplusplus/afl-fuzz -i in -o out -m none -- ./bmpgrayscale @@ output.bmp
```
## Recover 
```bash
Export CC=gcc-10
```