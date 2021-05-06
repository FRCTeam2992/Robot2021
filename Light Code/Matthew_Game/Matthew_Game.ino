#include <Adafruit_NeoPixel.h>

// Spindexer Lights
#define SPINDEXER_PIN 6
#define SPINDEXER_COUNT 72
#define SPINDEXER_REAL_COUNT 145

// Spindexer Lights
Adafruit_NeoPixel spindexerStrip = Adafruit_NeoPixel(SPINDEXER_REAL_COUNT, SPINDEXER_PIN, NEO_GRB + NEO_KHZ800);

// Color Codes
uint32_t red = spindexerStrip.Color(255, 0, 0);
uint32_t orange = spindexerStrip.Color(255, 50, 0);
uint32_t yellow = spindexerStrip.Color(255, 160, 0);
uint32_t green = spindexerStrip.Color(0, 255, 0);
uint32_t blue = spindexerStrip.Color(0, 0, 255);
uint32_t purple = spindexerStrip.Color(255, 0, 255);

// Game Enums
enum GameState {
  MENU,
  PLAYING,
  END
};

// Game Settings

// Game Variables
GameState lastProcessedGameState = MENU;
GameState currentGameState = MENU;
GameState desiredGameState = MENU;
boolean serialWasConnected = false;


void setup() {
  // Spindexer Lights
  spindexerStrip.setBrightness(255);
  spindexerStrip.begin();
  spindexerStrip.show();

  // Open Serial
  Serial.begin(9600);
}

void loop() {
  // Wait for Valid Serial Connection
  while (!Serial) {
    serialWasConnected = false;
  }

  if (!serialWasConnected) {
    // Send the Menu
    sendMenu();

    serialWasConnected = true;
  }

  // Update the Current Game State to the Desired Game State
  currentGameState = desiredGameState;

  // Process the Current Game State
  if (currentGameState == MENU) {

    // Check for State Change
    if (lastProcessedGameState == END) {
      // Clear the Console
      clearConsole();

      // Send the Menu
      sendMenu();
    }

    // Process the Menu
    processMenu();
  } else if (currentGameState == PLAYING) {

    // Check for State Change
    if (lastProcessedGameState == MENU) {
      // Clear the Console
      clearConsole();

      // Send the Game Instructions
      sendGameInstructions();
    }

    // Process the Play State
    processPlay();
  } else {

    // Process the End State
    processEnd();
  }

  // Update the Last Processed Game State
  lastProcessedGameState = currentGameState;
}

void sendMenu() {
  // Print the Menu to the Serial Connection
  Serial.println("");
  Serial.println("---------------------------------- Matthew's Light Game ----------------------------------");
  Serial.println("");
  Serial.println("The goal of this game is to stop the moving light within the bounds.");
  Serial.println("As you progress, the bounds will become smaller and the light will start to speed up.");
  Serial.println("");
  Serial.println("MENU OPTIONS");
  Serial.println("S: Start Game");
  Serial.println("W: View Leaderboard");
  Serial.println("");
  Serial.println("------------------------------------------------------------------------------------------");
  Serial.println("");
}

void sendGameInstructions() {
  // Print the Game Instructions to the Serial Connection
  Serial.println("");
  Serial.println("---------------------------------- Game Instructions ----------------------------------");
  Serial.println("");
  Serial.println("The goal of this game is to stop the moving light within the bounds.");
  Serial.println("As you progress, the bounds will become smaller and the light will start to speed up.");
  Serial.println("");
  Serial.println("To stop the light, send 'A' through the console.");
  Serial.println("");
  Serial.println("---------------------------------------------------------------------------------------");
  Serial.println("");
  Serial.println("Game starting in 10 seconds...");
  Serial.println("");
}

void clearConsole() {
  // Clear the Console
  for (int i = 0; i < 30; i++) {
    Serial.println("");
  }
}

void processMenu() {
  // Check for Console Input
  if (Serial.available() > 0) {
    char receivedChar = Serial.read();

    // Process the Received Data
    if (receivedChar == 'S' || receivedChar == 's') {
      // Change the Game State
      desiredGameState = PLAYING;
    } else if (receivedChar == 'W' || receivedChar == 'w') {
      Serial.println("This is not available yet!");
    }
  }
}

void processPlay() {

}

void processEnd() {

}

void setSpindexerPixel(int index, int r, int g, int b) {
  spindexerStrip.setPixelColor(index, r, g, b);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, r, g, b);
}

void setSpindexerPixel(int index, uint32_t color) {
  spindexerStrip.setPixelColor(index, color);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, color);
}
