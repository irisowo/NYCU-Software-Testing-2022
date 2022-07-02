FROM ubuntu:20.04
MAINTAINER lab6

RUN apt-get update && \
    apt-get upgrade -y
RUN apt-get install libc6-dbg
RUN apt-get install g++ valgrind -y


RUN useradd -m lab6
RUN chown -R root:root /home/lab6
RUN chmod -R 755 /home/lab6