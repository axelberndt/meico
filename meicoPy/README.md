# meicoPy: MEI Converter - Python Integration

Author: [Axel Berndt](https://github.com/axelberndt)<br>
[Center of Music and Film Informatics](http://www.cemfi.de/), Detmold

This Python script demonstrates the usage of meico in Python. It resembles meico's native command line mode as implemented in `meico.app.MeicoApp.java`. It requires the package [JPype](https://github.com/originell/jpype) to be installed (`pip install JPype1-py3`) to run Java code in Python. A further prerequisite is to download the latest `meico.jar` from the [release page](https://github.com/cemfi/meico/releases/latest) into the same folder as `meicoPy.py` so it can be found by the script. And, of course, there has to be a Java Virtual Machine installed on the system.

Usage: `python3 meicoPy.py [OPTIONS] FILE`

| Option                            | Description                                                                                                                      |
|-----------------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| `-h`, `--help`                    | show this help text                                                                                                              |
| `-v`, `--validate`                | validate loaded MEI file                                                                                                         |
| `-a`, `--add-ids`                 | add missing `xml:id`s to note, rest and chord elements in MEI;<br>meico will output a revised MEI file                           |
| `-r`, `--resolve-copy-ofs`        | resolve elements with `copyof` attributes into selfcontained elements<br>with unique `xml:id`; meico will output a revised MEI file |
| `-e`, `--ignore-expansions`       | expansions in MEI indicate a rearrangement of the source material, use this option to prevent this step                          |
| `-m`, `--msm`                     | convert to MSM                                                                                                                   |
| `-i`, `--midi`                    | convert to MIDI (and internally to MSM)                                                                                          |
| `-p`, `--no-program-changes`      | suppress program change events in MIDI, all music will be played by piano                                                        |
| `-c`, `--dont-use-channel-10`     | do not use channel 10 (drum channel) in MIDI                                                                                     |
| `-t argument`, `--tempo argument` | set MIDI tempo (bpm), default is 120 bpm                                                                                         |
| `-w`, `--wav`                     | convert to Wave (and internally to MSM and MIDI)                                                                                 |
| `-3`, `--mp3`                     | convert to MP3 (and internally to MSM and MIDI)                                                                                  |
| `-s FILE`, `--soundbank FILE`     | use a specific sound bank file (.sf2, .dls) for Wave conversion                                                                  |
| `-d`, `--debug`                   | write additional debug versions of MEI and MSM                                                                                   |


The final argument should always be a path to a valid MEI file (e.g., `"C:\myMeiCollection\test.mei"`); always in quotes! This is the only mandatory argument if you want to convert something.

### License information
Meico REST API utilizes the following third-party libraries:
- [JPype](https://pypi.python.org/pypi/JPype1) v1 0.6.2, Apache License V2.0.