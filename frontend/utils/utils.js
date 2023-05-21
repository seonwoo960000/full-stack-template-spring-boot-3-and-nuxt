import constant from "@/constants/common";
import {openPrimarySnackBar} from "@/utils/events";

let fileReader

export async function copyToClipboardWithMessage(text) {
  await navigator.clipboard.writeText(text)
  openPrimarySnackBar(constant.messages.copyToClipboard)
}

export function copyToClipboard(text) {
  const elem = document.createElement('textarea');
  elem.value = text;
  document.body.appendChild(elem);
  elem.select();
  document.execCommand('copy');
  document.body.removeChild(elem);
  openPrimarySnackBar(constant.messages.copyToClipboard)
}
export async function resizeImage({file, maxSize = 2000}) {
  if (!file) {
    return new Promise((resolve, reject) => reject("No file to resize"))
  }

  if (!fileReader) {
    fileReader = new FileReader()
  }
  const image = new Image()
  const canvas = document.createElement('canvas')
  const fileName = file.name

  return new Promise((resolve, reject) => {
    if (!file.type.match(/image.*/)) {
      return reject("Not an image")
    }

    fileReader.onload = (fileReaderEvent) => {
      image.onload = function () {
        resolve(new File([resize(image, canvas, maxSize)], fileName))
      }
      image.src = fileReaderEvent.target.result
    }

    fileReader.readAsDataURL(file)
  })
}

export function dataUriToBlob(dataURI) {
  // convert base64 to raw binary data held in a string
  // doesn't handle URLEncoded DataURIs - see SO answer #6850276 for code that does this
  let byteString = atob(dataURI.split(',')[1]);

  // separate out the mime component
  let mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

  // write the bytes of the string to an ArrayBuffer
  let ab = new ArrayBuffer(byteString.length);
  let ia = new Uint8Array(ab);
  for (let i = 0; i < byteString.length; i++) {
    ia[i] = byteString.charCodeAt(i);
  }
  return new Blob([ab], {type: mimeString});
}

const resize = function (image, canvas, maxSize) {
  let width = image.width;
  let height = image.height;
  if (width > height) {
    if (width > maxSize) {
      height *= maxSize / width;
      width = maxSize;
    }
  } else {
    if (height > maxSize) {
      width *= maxSize / height;
      height = maxSize;
    }
  }
  canvas.width = width;
  canvas.height = height;
  canvas.getContext('2d').drawImage(image, 0, 0, width, height);
  let dataUrl = canvas.toDataURL('image/jpeg');
  return dataUriToBlob(dataUrl);
}

export function convertNumberToMoneyFormat(number) {
  return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
}

