#!/bin/bash
Xvfb :99 -screen 0 640x480x8 -nolisten tcp &
java -Dprism.order=sw -jar Server-1.0-SNAPSHOT.jar