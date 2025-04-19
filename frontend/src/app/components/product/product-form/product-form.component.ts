import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ProductService } from '../../../services/product.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  productForm: FormGroup;
  isEditMode: boolean = false;
  productId: number | null = null;
  loading: boolean = false;
  submitted: boolean = false;
  categories: any[] = [
    { label: 'Metais', value: 'Metais' },
    { label: 'Tubulações', value: 'Tubulações' },
    { label: 'Estruturas Metálicas', value: 'Estruturas Metálicas' },
    { label: 'Soldagem', value: 'Soldagem' },
    { label: 'Chapas de Aço', value: 'Chapas de Aço' },
    { label: 'Equipamentos Industriais', value: 'Equipamentos Industriais' },
    { label: 'Manutenção', value: 'Manutenção' },
    { label: 'Outros', value: 'Outros' }
  ];

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService
  ) {
    this.productForm = this.fb.group({
      descricao: ['', [Validators.required]],
      marca: ['', [Validators.required]],
      categoria: ['', [Validators.required]],
      codigoBarras: ['', [Validators.required]],
      codigoIdentificacao: ['', [Validators.required]],
      local: [''],
      responsavel: ['', [Validators.required]],
      voltagem: ['', [Validators.required]],
      valor: [0, [Validators.required, Validators.min(0)]],
      stockQuantity: [0, [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.productId = +id;
      this.loadProduct(this.productId);
    }
  }

  loadProduct(id: number): void {
    this.loading = true;
    this.productService.getProductById(id).subscribe({
      next: (product) => {
        this.productForm.patchValue(product);
        this.loading = false;
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Failed to load product: ' + error.message
        });
        this.loading = false;
        this.router.navigate(['/products']);
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.productForm.invalid) {
      return;
    }

    this.loading = true;
    const product = this.productForm.value;

    if (this.isEditMode && this.productId) {
      this.productService.updateProduct(this.productId, product).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Product updated successfully'
          });
          this.loading = false;
          this.router.navigate(['/products']);
        },
        error: (error) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Failed to update product: ' + error.message
          });
          this.loading = false;
        }
      });
    } else {
      this.productService.createProduct(product).subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Success',
            detail: 'Product created successfully'
          });
          this.loading = false;
          this.router.navigate(['/products']);
        },
        error: (error) => {
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Failed to create product: ' + error.message
          });
          this.loading = false;
        }
      });
    }
  }

  cancel(): void {
    this.router.navigate(['/products']);
  }

  get f() {
    return this.productForm.controls;
  }
}
