# Window Module

## window

```c++
int main() {
  sf::Window window(sf::VideoMode(800, 600), "My window"); // videomode used to set the inner size of window
  while (window.isOpen()) { // game loop
    sf::Event event;
    while (window.pollEvent(event)) { // event loop
      if (event.type == sf::Event::Closed) { // check the event type
        window.close();
      }
    }
  }
  return 0;
}
```

## Event

### Event loop

You must use `pollEvent()` to fill events, or your events will not be valid.

```c++
sf::Event event;
while (window.pollEvent(event)){
  switch(event.type) {
    case sf::Event::Closed:
      window.close();
      break;
    case sf::Event::KeyPressed:
      cout << "key pressed";
      break;
    default:
      break;
  }
}
```

### [Event types](https://www.sfml-dev.org/documentation/2.5.1/classsf_1_1Event.php#af41fa9ed45c02449030699f671331d4a)

- Closed
- KeyPressed
  - Holding a key may generate multiple events. To disable repeated `KeyPressed` events, you can call `window.setKeyRepeatEnabled(false)`
  - use `event.key.code == sf::Keyboard::Escape` to check if the keyboard input is what you want
    -  `event.key.code` can access the code of the key. 'A' is 0, 'B' is 1, 'C' is 2, ...
  - [Keyboard](https://www.sfml-dev.org/documentation/2.5.1/classsf_1_1Keyboard.php) class
- MouseButtonPressed
  - `mouseButton.button` has 5 types: `sf::Mouse::left`, right, middle (wheel), extra #1 and extra #2 (side buttons)
  - get the position of mouse: `event.mouseButton.x`, `event.mouseButton.y`

## Keyboard & Mouse

Keyboard and Mouse are global input devices, which should not be confused with events. You can query the global state of keyboard and mouse at any time.

### Keyboard

- `sf::Keyboard::isKeyPressed(sf::Keyboard::Left)`

### Mouse

- `sf::Mouse::isButtonPressed(sf::Mouse::Left)`

- get position of the mouse:

  ```c++
  // get the global mouse position (relative to the desktop)
  sf::Vector2i globalPosition = sf::Mouse::getPosition();
  
  // get the local mouse position (relative to a window)
  sf::Vector2i localPosition = sf::Mouse::getPosition(window); // window is a sf::Window
  ```

# Graphics Module

## Drawing 2D stuffs

