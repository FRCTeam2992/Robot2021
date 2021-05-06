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
uint32_t blue = spindexerStrip.Color(0, 29, 220);
uint32_t purple = spindexerStrip.Color(255, 0, 255);
uint32_t white = spindexerStrip.Color(255, 255, 255);
uint32_t black = spindexerStrip.Color(0, 0, 0);

void setup() {
  // Spindexer Lights
  spindexerStrip.setBrightness(255);
  spindexerStrip.begin();
  spindexerStrip.show();
}

void loop() {

}

void setSpindexerPixel(int index, int r, int g, int b) {
  spindexerStrip.setPixelColor(index, r, g, b);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, r, g, b);
}

void setSpindexerPixel(int index, uint32_t color) {
  spindexerStrip.setPixelColor(index, color);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, color);
}
