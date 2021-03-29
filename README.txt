1. Resource generator folder contains a java program to generate the probabilities for the letter prediction.
  a. reads words from a textfile called words.txt
  b. creates a hashmap for each substring (always contains first letter) <String, float[]>
  c. serializes the hashmap and writes it into a file called hash.ser

2. Letter_Prediction is the android app with a basic prototype.
  a. reads the hash.ser file from the raw folder and populates a hashmap
  b. contains a single activity and layout with Views representing keyboard buttons
  c. uses hashMap to change the button colors
