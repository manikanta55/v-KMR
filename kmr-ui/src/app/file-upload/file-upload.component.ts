import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  fileName = '';

    constructor(private http: HttpClient) {}
  ngOnInit(): void {
    
  }

    onFileSelected(event) {

        const file:File = event.target.files[0];

        if (file) {
            this.fileName = file.name;
            const formData = new FormData();
            formData.append("thumbnail", file);
            const upload$ = this.http.post("/api/thumbnail-upload", formData);
            upload$.subscribe();
        }
    }

}
