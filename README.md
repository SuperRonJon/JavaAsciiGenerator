Run jar from command line

`java -jar ./Ascii-Generator.jar -ifs 0.3 ./path/to/image.png`

-i = invert colors, brightest colored pixels have the densest characters rather than darkest

-s val = scaling factor, scales the image by val before converting to ascii. Important for high resolution images.

-f = outputs to a file instead of to the console. Output filename will be image.png.txt (image.png is whatever original image was named)

-b = removes bottom right border that sometimes occurs on certain image outputs. You'll know if you need it.


![WindowsTerminal_oaIjW7fMUd](https://github.com/user-attachments/assets/7b8bb38a-e663-43d6-9183-f80794eac211)
