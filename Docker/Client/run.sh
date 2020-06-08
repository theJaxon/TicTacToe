#!/bin/bash
Xvfb :99 -screen 0 640x480x8 -nolisten tcp &
java -Dprism.order=sw -Dprism.verbose=true -jar TicTacToeFX-1.0-SNAPSHOT.jar