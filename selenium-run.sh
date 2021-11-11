# Stop the existing the zalenium docker container if its runnning
sudo docker stop zalenium

# Start the zalenium docker container in 8082 port
sudo /usr/bin/docker run --rm -ti --name zalenium -p 8082:4444 \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /tmp/videos:/home/seluser/videos \
      --privileged dosel/zalenium start --videoRecordingEnabled false  --desiredContainers 1