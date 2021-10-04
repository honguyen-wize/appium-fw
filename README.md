
# Find emulator name
$ emulator -list-avds
# Run locally with emulator
$ mvn test -DdeviceName=My_Emulator_-_Pixel_2

# Run with cloud
$ mvn test -Dcloud=true

$ mvn test -DdeviceName=My_Emulator_-_Pixel_2 -Dcloud=true