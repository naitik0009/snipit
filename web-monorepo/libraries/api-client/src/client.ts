import axios, {Axios, AxiosInstance, AxiosResponse} from 'axios';
import {Snippet, SnippetReq, LoginReq, SuccessMsg} from './types';

export class GenSevenClient{
    private client: AxiosInstance;
    constructor(baseUrl: string = 'http://localhost:8000/api'){
        this.client = axios.create({baseURL: baseUrl})
    }
    async register(data: LoginReq): Promise<SuccessMsg> {
    const res: AxiosResponse<SuccessMsg> = await this.client.post('/register', data);
    return res.data;
  }

  // --- 2. CREATE SNIPPET ---
  async createSnippet(data: SnippetReq): Promise<Snippet> {
    const res: AxiosResponse<Snippet> = await this.client.post('/snippets', data);
    return res.data;
  }

  // --- 3. LIST SNIPPETS ---
  async listSnippets(userId: string): Promise<Snippet[]> {
    const res: AxiosResponse<Snippet[]> = await this.client.get(`/snippets/${userId}`);
    return res.data;
  }
}