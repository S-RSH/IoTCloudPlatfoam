from gpiozero import Button

button = Button(2)

if __name__ == '__main__':
    while True:
        if button.is_pressed:
            print("button pressed!")
        else:
            print("press button!!")
