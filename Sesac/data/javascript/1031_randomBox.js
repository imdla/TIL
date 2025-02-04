const colorBox = document.createElement('div');
const objRef = document.body;
objRef.append(colorBox);

function makeRandomNum() {
  let randomNum = _.random(0, 255).toString(16);
  return randomNum;
}

let color = `#${makeRandomNum()}${makeRandomNum()}${makeRandomNum()}`;

colorBox.style.width = '100px';
colorBox.style.height = '100px';
colorBox.style.backgroundColor = color;
