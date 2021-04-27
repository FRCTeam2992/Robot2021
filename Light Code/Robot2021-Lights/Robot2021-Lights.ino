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

uint32_t cyan = spindexerStrip.Color(0, 255, 200);
uint32_t mint = spindexerStrip.Color(50, 255, 50);
uint32_t lime = spindexerStrip.Color(75, 255, 4);
uint32_t babyish_blue = spindexerStrip.Color(85, 85, 255);
uint32_t pink = spindexerStrip.Color(255, 60, 60);
uint32_t magenta = spindexerStrip.Color(255, 0, 155);
uint32_t turquoise = spindexerStrip.Color(0, 214, 179);

uint32_t black = spindexerStrip.Color(0, 0, 0);
uint32_t white = spindexerStrip.Color(255, 255, 255);

uint32_t rPurple = spindexerStrip.Color(85, 0, 220);
uint32_t rPink = spindexerStrip.Color(252, 3, 115);
uint32_t rBlue = spindexerStrip.Color(0, 67, 156);
uint32_t rGreenBlue = spindexerStrip.Color(0, 255, 157);
uint32_t rMint = spindexerStrip.Color(94, 255, 143);
uint32_t rLime = spindexerStrip.Color(0, 255, 64);

void setup() {
  // Spindexer Lights
  BackgroundColor();
  spindexerStrip.setBrightness(255);
  spindexerStrip.begin();
  spindexerStrip.show();
}

void loop() {
  //alternatingBlueWhiteBounce;
  //blueChase();
  //CJCode();
  //CJCodeButGood();
  //ColorTest();
  //5heartMeter();
  //ColorMixies();
<<<<<<< HEAD
  Dissolve();
=======
  rileyCode();
>>>>>>> 9ad63805187060352eea8f56021b4c2f8099e749
}
void BackgroundColor() {
  for (int i = 0; i <= 300; i++) {
    setSpindexerPixel(i, blue);
  }
  spindexerStrip.show();
  delay(10);
}

void RandomReRoll() {
  int fff = random (0, SPINDEXER_COUNT);
  if (spindexerStrip.getPixelColor(fff) == white) {
    RandomReRoll();
  }
}

void ColorTest() {
  for (int i = 1; i <= 99; i++) {
    setSpindexerPixel(i, lime);
    spindexerStrip.show();
  }
}


void Dissolve() {
  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    int randomPixel = random(0, SPINDEXER_COUNT);

    while (spindexerStrip.getPixelColor(randomPixel) == white) {
      randomPixel = random (0, SPINDEXER_COUNT);
    }

    setSpindexerPixel(randomPixel, white);

    spindexerStrip.show();

    delay(30);
  }

   for (int i = 0; i < SPINDEXER_COUNT; i++) {
    int randomPixel = random(0, SPINDEXER_COUNT);

    while (spindexerStrip.getPixelColor(randomPixel) == blue) {
      randomPixel = random (0, SPINDEXER_COUNT);
    }

    setSpindexerPixel(randomPixel, blue);

    spindexerStrip.show();

    delay(30);
  }
}


void alternatingBlueWhiteBounce() {
  bool wasBlue = false;

  for (int i = 0; i < SPINDEXER_COUNT; i += 2) {

    if (wasBlue) {
      setSpindexerPixel(i, white);
      wasBlue = false;
    } else {
      setSpindexerPixel(i, blue);
      wasBlue = true;
    }
    spindexerStrip.show();
    delay(20);
  }
  wasBlue = true;
  for (int i = 96; i >= 0; i -= 2) {
    if (wasBlue) {
      setSpindexerPixel(i, white);
      wasBlue = false;
    } else {
      setSpindexerPixel(i, blue);
      wasBlue = true;
    }
    for (int i = 96; i >= 0; i -= 2) {
      spindexerStrip.show();
      delay(20);
    }
  }
}
void setStripWhite() {
  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    setSpindexerPixel(i, white);
  }
  for (int i = 0; i < 10; i++) {
    setSpindexerPixel(i, blue);
  }
  spindexerStrip.show();
}
int qq = 75;
void heartMeter() {


  int distHeart = random (0, 99);
  for (int h = 0; h <= distHeart; h++) {
    qq -= .75;
    setSpindexerPixel(h, green);
    spindexerStrip.show();
    delay(qq);
  }


  for (int h = distHeart - 10; h >= -10; h--) {
    qq += 1;

    setSpindexerPixel(h, green);
    setSpindexerPixel(h + 10, black);
    spindexerStrip.show();
    delay(qq);
  }
  qq = 100;

}


int n = 76;
int f = 10;

void CJCodeButGood() {

  if (n <= 6) {
    n = 6;
    f = -10;
  }

  if (n >= 76) {
    n = 76;
    f = 10;

  }

  for (int i = 10; i <= 98; i++) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i - 10, white);
    spindexerStrip.show();
    delay(n);
  }
  n -= f;

  for (int i = 89; i >= 0; i--) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i + 10, white);
    spindexerStrip.show();
    delay(n);
  }
  if (n <= 6) {
    n = 6;
    f = -10;

  }

  if (n >= 76) {
    n = 76;
    f = 10;

  }
  n -= f;

}



void blueChase() {
  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i - 10, blue);

    if (i == 0) {
      setSpindexerPixel(SPINDEXER_COUNT - 10, black);
    } else if (i == 1) {
      setSpindexerPixel(SPINDEXER_COUNT - 9, black);
    } else if (i == 2) {
      setSpindexerPixel(SPINDEXER_COUNT - 8, black);
    } else if (i == 3) {
      setSpindexerPixel(SPINDEXER_COUNT - 7, black);
    } else if (i == 4) {
      setSpindexerPixel(SPINDEXER_COUNT - 6, black);
    } else if (i == 5) {
      setSpindexerPixel(SPINDEXER_COUNT - 5, black);
    } else if (i == 6) {
      setSpindexerPixel(SPINDEXER_COUNT - 4, black);
    } else if (i == 7) {
      setSpindexerPixel(SPINDEXER_COUNT - 3, black);
    } else if (i == 8) {
      setSpindexerPixel(SPINDEXER_COUNT - 2, black);
    } else if (i == 9) {
      setSpindexerPixel(SPINDEXER_COUNT - 1, black);
    } else if (i == 10) {
      setSpindexerPixel(SPINDEXER_COUNT, black);
    }

    spindexerStrip.show();
    delay(15);
  }
  for (int i = 88; i >= 0; i--) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i + 10, black);

    spindexerStrip.show();
    delay(5);
  }
  for (int i = 0; i <= 98; i++) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i - 10, blue);

    spindexerStrip.show();
    delay(15);
  }
  for (int i = 88; i >= 0; i--) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i + 10, black);

    spindexerStrip.show();
    delay(5);
  }
}

int a = 0;
int speed = 20;

void CJCode() {
  for (int i = 0; i < 99; i++) {
    setSpindexerPixel(i, white);
  }
  for (int i = 0; i < 4; i++) {
    setSpindexerPixel(i, red);
  }
  for (int i = 4; i < 8; i++) {
    setSpindexerPixel(i, yellow);
  }
  for (int i = 8; i < 12; i++) {
    setSpindexerPixel(i, orange);
  }
  for (int i = 12; i < 16; i++) {
    setSpindexerPixel(i, green);
  }
  for (int i = 16; i < 20; i++) {
    setSpindexerPixel(i, blue);
  }
  for (int i = 20; i < 24; i++) {
    setSpindexerPixel(i, rPurple);
  }

  for (int i = 0; i < 78; i++) {

    setSpindexerPixel(i, red);
    setSpindexerPixel(i + 4, orange);
    setSpindexerPixel(i + 8, yellow);
    setSpindexerPixel(i + 12, green);
    setSpindexerPixel(i + 16, blue);
    setSpindexerPixel(i + 20, rPurple);
    setSpindexerPixel(i - 4, white);

    if (i > 1) {
      delay(20);
    }
    spindexerStrip.show();
  }
  for (int i = 98; i >= 21; i--) {
    setSpindexerPixel(i, rPurple);
    setSpindexerPixel(i - 4, blue);
    setSpindexerPixel(i - 8, green);
    setSpindexerPixel(i - 12, yellow);
    setSpindexerPixel(i - 16, orange);
    setSpindexerPixel(i - 20, red);
    if (i <= 94) {
      setSpindexerPixel(i + 4, white);
    }
    if (i < 95) {
      delay(20);
    }

    spindexerStrip.show();

  }
}
int rd = random (126, 255);
int grn = random (126, 255);
int blu = random (126, 255);

int redd = rd;
int gren = grn;
int bllue = blu;

int reeed = random (126, 255);
int greeen = random (126, 255);
int blueee = random (126, 255);

int reed = reeed;
int grreen = greeen;
int bluee = blueee;

void ColorMixies() {



  for (int i = 98; i <= 98; i++) {
    setSpindexerPixel(i, red, green, blue);
    setSpindexerPixel(i - 25, black);
  }
}







int x = 0;

int p = 0;
int j = 0;
int k = 0;
int l = 255;

int q = 0;
int y = 255;
int u = 255;
int w = 255;

int b = 3;
int hh = 20;
void coollightsv2() {

  if (p == 7) {
    p = 0;
  }

  if (q == 7) {
    q = 0;
  }

  for (int i = 0; i < 98; i++) {

    if (p == 0) {
      j = 255;
      k = 0;
      l = 255;
    }


    if (p == 1) {
      j = 0;
      k = 0;
      l = 255;
    }


    if (p == 2) {
      j = 0;
      k = 255;
      l = 0;
    }


    if (p == 3) {
      j = 255;
      k = 160;
      l = 0;
    }


    if (p == 4) {
      j = 255;
      k = 50;
      l = 0;
    }


    if (p == 5) {
      j = 255;
      k = 0;
      l = 0;
    }


    if (p == 6) {
      j = 225;
      k = 60;
      l = 60;
    }




    if (q == 1) {
      y = 255;
      u = 0;
      w = 255;
    }


    if (q == 2) {
      y = 0;
      u = 0;
      w = 255;
    }


    if (q == 3) {
      y = 0;
      u = 255;
      w = 0;
    }


    if (q == 4) {
      y = 255;
      u = 160;
      w = 0;
    }


    if (q == 5) {
      y = 255;
      u = 50;
      w = 0;
    }


    if (q == 6) {
      y = 255;
      u = 0;
      w = 0;
    }


    if (q == 0) {
      y = 225;
      u = 60;
      w = 60;
    }
    setSpindexerPixel(i, j, k, l);
    //setSpindexerPixel(i, 255, 255, 255);
    //setSpindexerPixel(i - 5, j, k, l);

    if (i == 0) {
      setSpindexerPixel(93, y, u, w);
    }

    if (i == 0) {
      setSpindexerPixel(94, y, u, w);
    }

    if (i == 1) {
      setSpindexerPixel(95, y, u, w);
    }

    if (i == 2) {
      setSpindexerPixel(96, y, u, w);
    }

    if (i == 3) {
      setSpindexerPixel(97, y, u, w);
    }

    if (i == 4) {
      setSpindexerPixel(98, y, u, w);
    }

    if (b == 1); {
      int h = 30;
    }
    if (b == 2); {
      int hh = 25;
    }
    if (b == 3); {
      int hh = 20;
    }
    if (b == 4); {
      int hh = 80;
    }
    if (b == 5); {
      int hh = 80;
    }
    if (b == 6); {
      int hh = 70;
    }
    delay(hh);
    spindexerStrip.show();
  }
  int b = random (1, 6);
  p++;
  q++;
}

void rileyCode() {
  for (int i = 0; i < SPINDEXER_COUNT; i++) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i - 10, blue);

    if (i == 0) {
      setSpindexerPixel(SPINDEXER_COUNT - 10, white);
    } else if (i == 1) {
      setSpindexerPixel(SPINDEXER_COUNT - 9, white);
    } else if (i == 2) {
      setSpindexerPixel(SPINDEXER_COUNT - 8, white);
    } else if (i == 3) {
      setSpindexerPixel(SPINDEXER_COUNT - 7, white);
    } else if (i == 4) {
      setSpindexerPixel(SPINDEXER_COUNT - 6, white);
    } else if (i == 5) {
      setSpindexerPixel(SPINDEXER_COUNT - 5, white);
    } else if (i == 6) {
      setSpindexerPixel(SPINDEXER_COUNT - 4, white);
    } else if (i == 7) {
      setSpindexerPixel(SPINDEXER_COUNT - 3, white);
    } else if (i == 8) {
      setSpindexerPixel(SPINDEXER_COUNT - 2, white);
    } else if (i == 9) {
      setSpindexerPixel(SPINDEXER_COUNT - 1, white);
    } else if (i == 10) {
      setSpindexerPixel(SPINDEXER_COUNT, white);
    }

    spindexerStrip.show();
    delay(15);
  }
  for (int i = 88; i >= 0; i--) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i + 10, black);

    spindexerStrip.show();
    delay(5);
  }
  for (int i = 0; i <= 98; i++) {
    setSpindexerPixel(i, blue);
    setSpindexerPixel(i - 10, white);

    spindexerStrip.show();
    delay(15);
  }
  for (int i = 88; i >= 0; i--) {
    setSpindexerPixel(i, white);
    setSpindexerPixel(i + 10, black);

    spindexerStrip.show();
    delay(5);
  }
}




void setSpindexerPixel(int index, int r, int g, int b) {
  spindexerStrip.setPixelColor(index, r, g, b);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, r, g, b);
}

void setSpindexerPixel(int index, uint32_t color) {
  spindexerStrip.setPixelColor(index, color);
  spindexerStrip.setPixelColor((SPINDEXER_REAL_COUNT - 1) - index, color);
}
